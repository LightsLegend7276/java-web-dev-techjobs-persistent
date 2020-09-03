package org.launchcode.javawebdevtechjobspersistent.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Job extends AbstractEntity{

    @ManyToOne
    private Employer employer;

    @ManyToMany
    private List<Skill> skills = new ArrayList<>();

}
