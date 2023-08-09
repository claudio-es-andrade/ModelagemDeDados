package com.claudioESandrade.moviesControl.Entity;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.persistence.*; // for Spring Boot 3

import java.sql.Date;
import java.util.Objects;
// import javax.persistence.*; // for Spring Boot 2

@Entity
@DataSourceDefinition ( name = "movie", className = "Movie")
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "type")
    private Integer type;

    @Column(name = "name")
    private String name;

    @Column(name = "total_ep")
    private Integer total_ep;

    @Column(name = "atual_ep")
    private Integer atual_ep;

    @Column(name = "last_view")
    private Date last_view;

    public Movie() {  }

    public Movie(Integer id, Integer type, String name, Integer total_ep, Integer atual_ep, Date last_view) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.total_ep  = total_ep;
        this.atual_ep  = atual_ep;
        this.last_view = last_view;
    }

    // id getter and setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // type getter and setter
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    // name getter and setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // totalEp getter and setter
    public Integer getTotalEp() {
        return total_ep;
    }

    public void setTotalEp(int total_ep) {
        this.total_ep = total_ep;
    }

    // atualEp getter and setter
    public Integer getAtualEp() {
        return atual_ep;
    }

    public void setAtualEp(Integer atual_ep) {
        this.atual_ep = atual_ep;
    }

    // lastView getter and setter
    public Date getLastView() {
        return last_view;
    }

    public void setLastView(Date last_view) {
        this.last_view = last_view;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie Movie = (Movie) o;
        return getId() == Movie.getId() && getType() == Movie.getType() && getTotalEp() == Movie.getTotalEp() && getAtualEp() == Movie.getAtualEp() && Objects.equals( getName(), Movie.getName() ) && Objects.equals( getLastView(), Movie.getLastView() );
    }

    @Override
    public int hashCode() {
        return Objects.hash ( getId(), getType(), getName(), getTotalEp(), getAtualEp(), getLastView() );
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", totalEp=" + total_ep +
                ", atualEp=" + atual_ep +
                ", lastView=" + last_view +
                '}';
    }
}
