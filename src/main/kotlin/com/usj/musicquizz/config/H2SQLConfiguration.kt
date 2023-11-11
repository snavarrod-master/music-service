package com.usj.musicquizz.config

import com.usj.musicquizz.model.Song
import jakarta.persistence.ValidationMode
import org.h2.tools.Server
import org.hibernate.boot.MetadataSources
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.hibernate.cfg.Environment
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.sql.SQLException
import java.util.*
import javax.sql.DataSource


@Configuration
@EnableJpaRepositories(basePackages = ["com.usj.musicquizz"])
@EnableTransactionManagement
class H2SQLConfiguration {

    @Value("\${spring.datasource.config.base.package}")
    protected val basePackage: String? = null

    @Value("\${spring.jpa.database-platform}")
    protected val dialect: String? = null


    @Bean
    fun dataSource(): DataSource {
        val builder = EmbeddedDatabaseBuilder()
        return builder.setType(EmbeddedDatabaseType.H2)
            .build()
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    @Throws(SQLException::class)
    fun startDBManager(): Server {
        return Server.createWebServer()
    }

    @Bean
    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean? {
        val props = Properties()
        props["spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults"] = false
        props["hibernate.temp.use_jdbc_metadata_defaults"] = false
        props["hibernate.transaction.jta.platform"] = true
        val emf = LocalContainerEntityManagerFactoryBean()
        emf.dataSource = dataSource()
        emf.setPackagesToScan(basePackage)
        val jpaVendorAdapter = HibernateJpaVendorAdapter()
        jpaVendorAdapter.setDatabasePlatform(dialect)
        jpaVendorAdapter.setGenerateDdl(true)
        jpaVendorAdapter.setShowSql(false)
        emf.jpaVendorAdapter = jpaVendorAdapter
        emf.setJpaProperties(props)
        emf.setValidationMode(ValidationMode.AUTO)
        emf.afterPropertiesSet()
        return emf
    }
}