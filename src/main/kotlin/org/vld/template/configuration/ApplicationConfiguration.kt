package org.vld.template.configuration

import liquibase.integration.spring.SpringLiquibase
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
open class ApplicationConfiguration {

    @Bean
    open fun liquibaseDataSource(
            @Value("\${liquibase.datasource.url}") url: String = "OVERRIDEN_BY_EXPRESSION",
            @Value("\${liquibase.datasource.username}") username: String = "OVERRIDEN_BY_EXPRESSION",
            @Value("\${liquibase.datasource.password}") password: String = "OVERRIDEN_BY_EXPRESSION",
            @Value("\${liquibase.datasource.driver}") driver: String = "OVERRIDEN_BY_EXPRESSION"
    ): DataSource = DataSourceBuilder.create()
            .url(url)
            .username(username)
            .password(password)
            .driverClassName(driver)
            .build()

    @Bean
    open fun liquibase(
            @Value("\${liquibase.changeLog}") changeLog: String,
            @Value("\${liquibase.defaultSchema}") defaultSchema: String
    ): SpringLiquibase {
        val liquibase = SpringLiquibase()
        liquibase.dataSource = liquibaseDataSource()
        liquibase.changeLog = changeLog
        liquibase.defaultSchema = defaultSchema
        return liquibase
    }
}
