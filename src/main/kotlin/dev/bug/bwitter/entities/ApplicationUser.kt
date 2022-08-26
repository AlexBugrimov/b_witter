package dev.bug.bwitter.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.Hibernate
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "users")
data class ApplicationUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    val userId: Long?,

    @Column(name = "first_name")
    var firstName: String?,

    @Column(name = "last_name")
    var lastName: String?,

    @Column(unique = true)
    var email: String?,

    @Column(name = "phone")
    var phone: String?,

    @Column(name = "birth_date")
    val birthDate: LocalDate?,

    @Column(unique = true)
    var username: String?,

    @JsonIgnore
    var password: String?,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_role_in",
        joinColumns = [JoinColumn(name = "user_id")],
    inverseJoinColumns = [JoinColumn(name = "role_id")])
    val authorities: MutableSet<Role> = mutableSetOf(),

    val enable: Boolean = false,

    @Column(nullable = true)
    @JsonIgnore
    var verification: Long?
) {
    constructor(
        firstName: String?,
        lastName: String?,
        email: String?,
        birthDate: LocalDate?,
        phone: String?
    ) : this(null, firstName, lastName, email, phone, birthDate, null, null, mutableSetOf(), enable = false, null)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as ApplicationUser

        return userId == other.userId
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $userId , firstName = $firstName , lastName = $lastName , email = $email , phone = $phone , username = $username )"
    }
}
