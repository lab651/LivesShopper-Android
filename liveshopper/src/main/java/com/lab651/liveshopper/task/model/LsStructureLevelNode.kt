package com.lab651.liveshopper.task.model

data class LsStructureLevelNode(

    /**
     * The key associated with a entity
     */
    val key: String = "",

    /**
     * Date/time entity was created
     */
    val created: Long = 0,

    /**
     * Date/time entity was changed
     */
    val modified: Long = 0,

    /**
     * Version of the entity
     */
    val version: Long = 0,

    /**
     * The name of the level i.e. 'North Region', 'District 4'
     *
     *
     */
    val name: String = "",

    /**
     * The key of the parent level
     *
     *
     */
    val parentNodeKey: String = "",

    /**
     * The key of the structure level definition this node belongs to
     *
     *
     */
    val levelDefKey: String = "",

    /**
     * Owners of the Structure level
     *
     *
     */
    val owners: Owners = Owners()
)
