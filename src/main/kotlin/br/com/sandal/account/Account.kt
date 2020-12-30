package br.com.sandal.account

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "\"Account\"")
@EntityListeners(AuditingEntityListener::class)
@Inheritance(strategy = InheritanceType.JOINED)
open class Account : Serializable {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id:UUID = UUID.randomUUID()

    @Column(name = "person_id")
    open var personId:UUID? = null

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    open var createdAt:Date? = null

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    open var updatedAt:Date? = null
}