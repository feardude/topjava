package ru.javawebinar.topjava.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@NamedQueries({
        @NamedQuery(name = Meal.BY_ID_AND_USERID, query = "select m from Meal m where m.id=?1 and m.user.id=?2"),

        @NamedQuery(name = Meal.ALL_SORTED, query = "select m from Meal m " +
                                                    "where m.user.id=?1 " +
                                                    "order by m.dateTime desc"),

        @NamedQuery(name = Meal.DELETE, query = "delete from Meal m " +
                                                "where m.id=:id " +
                                                "and m.user.id=:userId"),

        @NamedQuery(name = Meal.UPDATE, query = "update Meal m " +
                                                "set m.description = :description, " +
                                                "m.dateTime = :dateTime, " +
                                                "m.calories = :calories " +
                                                "where m.user.id = :userId " +
                                                "and m.id = :id")
})
@Entity
@Table(name = "meals", uniqueConstraints = @UniqueConstraint(name = "meals_unique_user_datetime_idx",
                                                             columnNames = {"user_id", "datetime"})
)
public class Meal extends BaseEntity {

    public static final String BY_ID_AND_USERID = "Meal.getByIdAndUserid";
    public static final String ALL_SORTED = "Meal.getAllSorted";
    public static final String DELETE = "Meal.delete";
    public static final String UPDATE = "Meal.update";

    @Column(name = "datetime", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "description", nullable = false)
    @NotEmpty
    private String description;

    @Column(name = "calories", nullable = false)
    private int calories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Meal() {
    }

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public String getDescription() {
        return description;
    }
    public int getCalories() {
        return calories;
    }
    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }
    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setCalories(int calories) {
        this.calories = calories;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
