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
    val firstName: String,

    @Column(name = "last_name")
    val lastName: String,

    @Column(unique = true)
    val email: String,

    @Column(name = "phone")
    val phone: String?,

    @Column(name = "birth_date")
    val birthDate: LocalDate,

    @Column(unique = true)
    var username: String?,

    @JsonIgnore
    val password: String?,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_role_in",
        joinColumns = [JoinColumn(name = "user_id")],
    inverseJoinColumns = [JoinColumn(name = "role_id")])
    val authorities: MutableSet<Role> = mutableSetOf()
) {

    constructor(
        firstName: String,
        lastName: String,
        email: String,
        birthDate: LocalDate,
    ) : this(null, firstName, lastName, email, null, birthDate, null, null, mutableSetOf())

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
