package com.lab651.liveshopper.task.model

abstract class Entity {
    /**
     * The key associated with a entity
     */
    val key: String = ""

    /**
     * Date/time entity was created
     */
    val created: Long? = null

    /**
     * Date/time entity was changed
     */
    val modified: Long? = null

    /**
     * Version of the entity
     */
     val version: Long? = null
}