package org.launchcode.javawebdevtechjobspersistent.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Employer extends AbstractEntity {

    @NotBlank(message = "This field cannot be blank.")
    @Size(max = 100, message = "Location name is too long.")
    private String location;

}
