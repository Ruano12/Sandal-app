package br.com.sandal.account.digital

import br.com.sandal.account.Account
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "\"DigitalAccount\"")
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
class DigitalAccount : Account(), Serializable {


    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    override var id:UUID = UUID.randomUUID()

    @Column(name = "account_number")
    open var accountNumber:String? = null
}