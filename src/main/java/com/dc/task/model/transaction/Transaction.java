package com.dc.task.model.transaction;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "transactions")
public class Transaction {

    @Id
    public ObjectId _id = ObjectId.get();
    public Integer transaction_id;
    @Field("transaction_amount")
    public String amount;
    @Indexed
    @Field("account_type")
    public Integer type;
    @Indexed
    @Field("customer_id")
    public Integer cid;
    public String transaction_date;
}
