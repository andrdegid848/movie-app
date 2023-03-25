package com.example.application.database.entity;

import java.io.Serializable;

public interface BaseEntity<T extends Serializable> {
    T getId();
}
