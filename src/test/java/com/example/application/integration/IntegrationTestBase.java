package com.example.application.integration;

import com.example.application.integration.annotation.IT;
import org.springframework.test.context.jdbc.Sql;

@IT
@Sql({
        "classpath:sql/data.sql"
})
public class IntegrationTestBase {
}
