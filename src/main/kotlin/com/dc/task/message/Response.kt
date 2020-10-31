package com.dc.task.message

class Response private constructor(
        val transaction_date: String?,
        val transaction_amount: String?,
        val transaction_id: Number?,
        val name: String?,
        val first_name: String?,
        val last_name: String?) {

    data class Builder(
            var transaction_date: String? = null,
            var transaction_amount: String? = null,
            var transaction_id: Number? = null,
            var name: String? = null,
            var first_name: String? = null,
            var last_name: String? = null) {

        fun transaction_date(transaction_date: String) = apply { this.transaction_date = transaction_date }
        fun transaction_amount(transaction_amount: String) = apply { this.transaction_amount = transaction_amount }
        fun transaction_id(transaction_id: Number) = apply { this.transaction_id = transaction_id }
        fun name(name: String) = apply { this.name = name }
        fun first_name(first_name: String) = apply { this.first_name = first_name }
        fun last_name(last_name: String) = apply { this.last_name = last_name }
        fun build() = Response(transaction_date, transaction_amount, transaction_id, name, first_name, last_name)
    }
}

