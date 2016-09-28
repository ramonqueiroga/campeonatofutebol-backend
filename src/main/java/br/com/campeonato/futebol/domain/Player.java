package br.com.campeonato.futebol.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Ramon on 07-Sep-16.
 */
@Entity
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String Sobrenome;
    private Integer hability;
    @ManyToOne
    private SoccerTeam soccerTeam;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSobrenome() {
        return Sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        Sobrenome = sobrenome;
    }

    public Integer getHability() {
        return hability;
    }

    public void setHability(Integer hability) {
        this.hability = hability;
    }

    @JsonIgnore
    public SoccerTeam getSoccerTeam() {
        return soccerTeam;
    }

    public void setSoccerTeam(SoccerTeam soccerTeam) {
        this.soccerTeam = soccerTeam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (id != null ? !id.equals(player.id) : player.id != null) return false;
        if (name != null ? !name.equals(player.name) : player.name != null) return false;
        if (Sobrenome != null ? !Sobrenome.equals(player.Sobrenome) : player.Sobrenome != null) return false;
        if (hability != null ? !hability.equals(player.hability) : player.hability != null) return false;
        return soccerTeam != null ? soccerTeam.equals(player.soccerTeam) : player.soccerTeam == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (Sobrenome != null ? Sobrenome.hashCode() : 0);
        result = 31 * result + (hability != null ? hability.hashCode() : 0);
        result = 31 * result + (soccerTeam != null ? soccerTeam.hashCode() : 0);
        return result;
    }

}
