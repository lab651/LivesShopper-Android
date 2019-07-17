package com.lab651.liveshopper.task.model

data class AccountInfo(
    /**
     * The address of the account
     */
    val address: String,

    /**
     * Details of the address
     * @example: Apartment B
     */
    val addressDetail: String,

    /**
     * The city where the account is located
     */
    val city: String,

    /**
     * The company name on the account
     */
    val companyName: String,

    /**
     * The country where the account is located
     */
    val country: String,

    /**
     * The email address on the account
     */
    val emailAddress: String,

    /**
     * First name on the account
     */
    val firstName: String,

    /**
     * The type of industry the account is associated with
     *
     * @example Automotive, Food Service, etc.
     */
    val industry: String,

    /**
     * Last name on the account
     */
    val lastName: String,

    /**
     * Managed nodes in account
     */
    val managedNodes: Map<String, String>,

    /**
     * The password for the account
     */
    val password: String,

    /**
     * Confirms password for account
     */
    val passwordDuplicate: String,

    /**
     * Phone number on account
     */
    val phoneNumber: String,

    /**
     * What countries phone number?
     */
    val phoneNumberLocale: String,

    /**
     * Roles associated with the account
     *
     * @example: Client
     */
    val roles: List<String>,

    /**
     * Is the account associated with a state or province?
     */
    val stateOrProvince: String,

    /**
     * Key referring to specific user
     */
    val userKey: String,

    /**
     * The zip code of the account location
     */
    val zipCode: String,

    /**
     * What countries zip code?
     */
    val zipCodeLocale: String
)
