package de.szut.dqi12.onlinepoker.server.comm.packet;

import de.szut.dqi12.onlinepoker.server.comm.packet.entity.EntityType;

/**
 * Created by Kevin on 29.02.2016.
 */
public class Entity {
    public static final String KEY_ACTION = "Action";

    protected EntityType entityType;

    public Entity(EntityType entityType) {
        this.entityType = entityType;
    }

    public EntityType getEntityType() {
        return entityType;
    }
}

