package com.dc.task.config;

import com.dc.task.model.transaction.Transaction;
import com.dc.task.model.account.Account;
import com.dc.task.model.customer.Customer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;

@EnableBatchProcessing
@Configuration
public class CsvToMongoDB {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Bean
    public Job readAccountsCSVFile() {
        return jobBuilderFactory.get("readAccountsCSVFile").incrementer(new RunIdIncrementer()).start(step1())
                .build();
    }

    @Bean
    public Job readTransactionsCSVFile() {
        return jobBuilderFactory.get("readTransactionsCSVFile").incrementer(new RunIdIncrementer()).start(step2())
                .build();
    }

    @Bean
    public Job readCustomersCSVFile() {
        return jobBuilderFactory.get("readCustomersCSVFile").incrementer(new RunIdIncrementer()).start(step3())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1").<Account, Account>chunk(10).reader(readerAccounts())
                .writer(writerAccounts()).build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2").<Transaction, Transaction>chunk(10).reader(readerTransactions())
                .writer(writerTransactions()).build();
    }

    @Bean
    public Step step3() {
        return stepBuilderFactory.get("step3").<Customer, Customer>chunk(10).reader(readerCustomers())
                .writer(writerCustomers()).build();
    }

    @Bean
    public FlatFileItemReader<Account> readerAccounts() {
        FlatFileItemReader<Account> reader = new FlatFileItemReader<Account>();
        reader.setResource(new ClassPathResource("accountypes.csv"));
        reader.setLineMapper(new DefaultLineMapper<Account>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("type", "name");

            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Account>() {{
                setTargetType(Account.class);
            }});
        }});
        return reader;
    }

    @Bean
    public FlatFileItemReader<Transaction> readerTransactions() {
        FlatFileItemReader<Transaction> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("transactions.csv"));
        reader.setLineMapper(new DefaultLineMapper<Transaction>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("transaction_id", "amount", "type", "cid", "transaction_date");

            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Transaction>() {{
                setTargetType(Transaction.class);
            }});
        }});
        return reader;
    }

    @Bean
    public FlatFileItemReader<Customer> readerCustomers() {
        FlatFileItemReader<Customer> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("customers.csv"));
        reader.setLineMapper(new DefaultLineMapper<Customer>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("id", "first_name", "last_name", "last_login_balance");

            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Customer>() {{
                setTargetType(Customer.class);
            }});
        }});
        return reader;
    }
    
    @Bean
    public MongoItemWriter<Account> writerAccounts() {
        MongoItemWriter<Account> writer = new MongoItemWriter<Account>();
        writer.setTemplate(mongoTemplate);
        writer.setCollection("account_types");
        return writer;
    }

    @Bean
    public MongoItemWriter<Transaction> writerTransactions() {
        MongoItemWriter<Transaction> writer = new MongoItemWriter<Transaction>();
        writer.setTemplate(mongoTemplate);
        writer.setCollection("transactions");
        return writer;
    }

    @Bean
    public MongoItemWriter<Customer> writerCustomers() {
        MongoItemWriter<Customer> writer = new MongoItemWriter<Customer>();
        writer.setTemplate(mongoTemplate);
        writer.setCollection("customers");
        return writer;
    }
}
