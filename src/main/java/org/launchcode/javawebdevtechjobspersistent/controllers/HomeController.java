package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Job;
import org.launchcode.javawebdevtechjobspersistent.models.Skill;
import org.launchcode.javawebdevtechjobspersistent.models.data.EmployerRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.JobRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JobRepository jobRepository;

    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "My Jobs");
        model.addAttribute("jobs", jobRepository.findAll());
        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob, Errors errors, Model model,
                                    @RequestParam int employerId, @RequestParam List<Integer> skills) {
        /*First check that happens is if newJob is Valid. The @Valid annotation above does this behind the scenes.
        * If it has errors we fall into this first if and we return them to the form to fill out.
        * The title passed into the attribute notifies them something went wrong.
        * */
        if (errors.hasErrors()) {
            model.addAttribute("title", "Invalid form Entry: Employer and Skill(s) must be selected");
            model.addAttribute("employers", employerRepository.findAll());
            model.addAttribute("skills", skillRepository.findAll());
            return "add";
        }
        /*
        * This checks to make sure that the employer selected still exists in our db. Very rare that this condition is
        * ever hit, but the check is still here just in case. Something similar could be done for skills, but for now I
        * didn't add that check
        */
        if (employerRepository.findById(employerId).isEmpty()) {
            model.addAttribute(model.addAttribute("title", "The selected employer no longer exists in our database."));
            model.addAttribute("employers", employerRepository.findAll());
            model.addAttribute("skills", skillRepository.findAll());
            return "add";
            /*
            * If everything is good then we set the employer field and skills field inside of the newJob.
            */
        } else {
            newJob.setEmployer(employerRepository.findById(employerId).get());
            newJob.setSkills((List<Skill>) skillRepository.findAllById(skills));
        }
        /*
        * Finally, we save the newJob Object in our DB with the correct values.
        */
        jobRepository.save(newJob);
        return "redirect:";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {
        if (jobRepository.findById(jobId).isEmpty()) {
            return "redirect:";
        } else {
            model.addAttribute("job", jobRepository.findById(jobId).get());
            return "view";
        }
    }


}
