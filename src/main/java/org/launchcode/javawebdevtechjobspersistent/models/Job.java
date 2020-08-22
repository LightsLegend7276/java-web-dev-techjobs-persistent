package org.launchcode.javawebdevtechjobspersistent.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Job extends AbstractEntity{

    @ManyToOne
    private Employer employer;

    private String skills;

}
