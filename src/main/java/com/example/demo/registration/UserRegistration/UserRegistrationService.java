package com.example.demo.registration.UserRegistration;

import com.example.demo.AppMVC.AppUserInformation.AppUserInformationModel;
import com.example.demo.AppMVC.AppUserInformation.AppUserInformationRepository;
import com.example.demo.AppMVC.AppUserInformation.AppUserInformationService;
import com.example.demo.AppMVC.AppUserInformation.UserEducation.UserEducationModel;
import com.example.demo.AppMVC.AppUserInformation.UserEducation.UserEducationService;
import com.example.demo.AppMVC.AppUserInformation.UserInterest.UserInterestModel;
import com.example.demo.AppMVC.AppUserInformation.UserInterest.UserInterestService;
import com.example.demo.AppMVC.AppUserInformation.UserProject.UserProjectModel;
import com.example.demo.AppMVC.AppUserInformation.UserProject.UserProjectService;
import com.example.demo.AppMVC.AppUserInformation.UserWork.UserWorkModel;
import com.example.demo.AppMVC.AppUserInformation.UserWork.UserWorkService;


import com.example.demo.AppMVC.AppUserModel;
import com.example.demo.AppMVC.UserRepository;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserRegistrationService {

    private final AppUserInformationService appUserInformationService;

    private final AppUserInformationRepository appUserInformationRepository;

    private final UserEducationService userEducationService;

    private final UserWorkService userWorkService;

    private final UserProjectService userProjectService;

    private final UserInterestService userInterestService;

    private final UserRepository userRepository;

    public String update(Long appUserId, UserRegistrationUpdateRequest request) {
        AppUserModel appUserModel = userRepository.findById(appUserId).get();
        AppUserInformationModel appUserInformationModel = appUserInformationRepository.findByAppUserId(appUserModel);

        appUserInformationModel.setResumeURL(request.getResumeURL());
        appUserInformationModel.setProfilePicURL(request.getProfilePicURL());
        appUserInformationModel.setCoverLetterURL(request.getCoverLetterURL());
        appUserInformationModel.setWebsiteURL(request.getWebsiteURL());
        appUserInformationModel.setBio(request.getBio());
        appUserInformationModel.setSkills(request.getSkills());

        appUserInformationService.saveAppUserInformation(appUserInformationModel);
        return "updated";
    }

    public String registerEducation(Long appUserId, UserRegistrationEductionRequest request) {
        AppUserModel appUserModel = userRepository.findById(appUserId).get();
        UserEducationModel userEducationModel = new UserEducationModel(
                request.getEducation(),
                request.getDegree(),
                request.getMajor(),
                request.getGPA(),
                request.getClubs(),
                request.getCourses(),
                request.getStartDate(),
                request.getGraduationDate(),
                appUserModel
        );

        userEducationService.saveAppUserInformation(userEducationModel);
        return "update education";
    }

    public String registerInterest(Long appUserId, UserRegistrationInterestRequest request) {
        AppUserModel appUserModel = userRepository.findById(appUserId).get();
        UserInterestModel userInterestModel = new UserInterestModel(
                request.getIndustries(),
                request.getLocation(),
                request.getRoles(),
                appUserModel
        );

        userInterestService.saveAppUserInformation(userInterestModel);
        return "update interest";
    }

    public String registerProject(Long appUserId, UserRegistrationProjectRequest request) {
        AppUserModel appUserModel = userRepository.findById(appUserId).get();
        UserProjectModel userProjectModel = new UserProjectModel(
                request.getName(),
                request.getDescription(),
                request.getPictures(),
                request.getRelevantWebsites(),
                appUserModel
        );

        userProjectService.saveAppUserInformation(userProjectModel);
        return "update project";
    }

    public String registerWork(Long appUserId, UserRegistrationWorkRequest request) {
        AppUserModel appUserModel = userRepository.findById(appUserId).get();
        UserWorkModel userWorkModel = new UserWorkModel(
                request.getCompany(),
                request.getTitle(),
                request.getLocation(),
                request.getStartDate(),
                request.getEndDate(),
                request.getDescription(),
                appUserModel
        );

        userWorkService.saveAppUserInformation(userWorkModel);
        return "update work";
    }

    public List<UserEducationModel> getEducation(Long appUserId){
        AppUserModel appUserModel = userRepository.findById(appUserId).get();
        return userEducationService.getEducation(appUserModel);
    }

    public List<UserInterestModel> getInterests(Long appUserId){
        AppUserModel appUserModel = userRepository.findById(appUserId).get();
        return userInterestService.getInterests(appUserModel);
    }

    public List<UserProjectModel> getProject(Long appUserId){
        AppUserModel appUserModel = userRepository.findById(appUserId).get();
        return userProjectService.getProject(appUserModel);
    }

    public List<UserWorkModel> getWork(Long appUserId){
        AppUserModel appUserModel = userRepository.findById(appUserId).get();
        return userWorkService.getWork(appUserModel);
    }

    public AppUserInformationModel getUserInformation(Long appUserId) {
        AppUserModel appUserModel = userRepository.findById(appUserId).get();
        return appUserInformationService.getUserInformation(appUserModel);
    }
}
