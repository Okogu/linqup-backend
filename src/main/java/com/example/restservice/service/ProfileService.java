package com.example.restservice.service;

import com.example.restservice.dto.ProfileDto;
import com.example.restservice.dto.ReceiveProfileDto;
import com.example.restservice.entity.Account;
import com.example.restservice.entity.Contact;
import com.example.restservice.entity.Profile;
import com.example.restservice.repository.ProfileRepository;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service

public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ContactService contactService;

    //profile controller depends on profile service
    public Profile createProfile(ProfileDto profileDto, String email) throws Exception {
        Account existingAccountOptional = accountService.findByEmail(email);//revert

        if (existingAccountOptional == null) {
            throw new Exception("Account does not exist");
        }

        Profile returnValue = new Profile();
        returnValue.setProfileName(profileDto.getProfileName());
        returnValue.setName(profileDto.getName());
        returnValue.setPhotoUrl(profileDto.getPhotoUrl());
        returnValue.setLogoUrl(profileDto.getLogoUrl());
        returnValue.setTitle(profileDto.getTitle());
        returnValue.setCompany(profileDto.getCompany());
        returnValue.setPhone(profileDto.getPhone());
        returnValue.setEmail(profileDto.getEmail());
        returnValue.setAddress(profileDto.getAddress());
        returnValue.setInstagram(profileDto.getInstagram());
        returnValue.setTiktok(profileDto.getTiktok());
        returnValue.setFacebook(profileDto.getFacebook());
        returnValue.setTwitter(profileDto.getTwitter());
        returnValue.setLinkedin(profileDto.getLinkedin());
        returnValue.setWebsite(profileDto.getWebsite());
        returnValue.setDefaultProfile(profileDto.getDefaultProfile());//boolean
        if (profileDto.getDefaultProfile()) {
            Profile defaultProfile = profileRepository.findByDefaultProfile(true);
            if (defaultProfile != null) {
                defaultProfile.setDefaultProfile(false);
                profileRepository.save(defaultProfile);
            }
        }
        returnValue.setAccount(existingAccountOptional);
        UUID uuid = UUID.randomUUID();
        returnValue.setSpecialId(uuid.toString());
//Todo: delegate qr generator to the ui
        ByteArrayOutputStream stream = QRCode
                .from(returnValue.getSpecialId())
                .withSize(250, 250)
                .stream();
        ByteArrayInputStream bis = new ByteArrayInputStream(stream.toByteArray());


        File outputFile = new File("C:\\Users\\okogu\\Downloads\\rest-service\\rest-service\\src\\main\\resources\\QRCodeImage\\" + returnValue.getSpecialId() + ".png");
        ImageIO.write(ImageIO.read(bis), "png", outputFile);

        return profileRepository.save(returnValue);
    }

    public List<Profile> fetchProfiles(String email) throws Exception {
        Account existingAccountOptional = accountService.findByEmail(email);
        if (existingAccountOptional == null) {
            throw new Exception("Account with email: " + email + " does not exist");
        } else {
            return profileRepository.findByAccount(existingAccountOptional);
        }
    }

    public Profile viewProfile(String profileName) {
        return profileRepository.findByProfileName(profileName);
    }

    public void deleteProfile(String profileName) throws Exception {
        Profile profile = profileRepository.findByProfileName(profileName);
        if (profileName == null) {
            throw new Exception("Profile " + profile + " does not exist");
        } else {
            profileRepository.deleteById(profile.getId());
        }
    }

    public Profile updateProfile(Profile profile) throws Exception {
        Optional<Profile> existingProfileOptional = profileRepository.findById(profile.getId());

        if (existingProfileOptional.isEmpty()) {
            throw new Exception("Profile does not exist");
        } else {
            Profile existingProfile = existingProfileOptional.get();
            existingProfile.setProfileName(profile.getProfileName());
            existingProfile.setName(profile.getName());
            existingProfile.setPhotoUrl(profile.getPhotoUrl());
            existingProfile.setLogoUrl(profile.getLogoUrl());
            existingProfile.setTitle(profile.getTitle());
            existingProfile.setCompany(profile.getCompany());
            existingProfile.setPhone(profile.getPhone());
            existingProfile.setEmail(profile.getEmail());
            existingProfile.setAddress(profile.getAddress());
            existingProfile.setInstagram(profile.getInstagram());
            existingProfile.setTiktok(profile.getTiktok());
            existingProfile.setFacebook(profile.getFacebook());
            existingProfile.setTwitter(profile.getTwitter());
            existingProfile.setLinkedin(profile.getLinkedin());
            existingProfile.setWebsite(profile.getWebsite());
            existingProfile.setDefaultProfile(profile.getDefaultProfile());//boolean
            if (profile.getDefaultProfile()) {
                Profile defaultProfile = profileRepository.findByDefaultProfile(true);
                if (defaultProfile != null) {
                    defaultProfile.setDefaultProfile(false);
                    profileRepository.save(defaultProfile);
                }
            }
            profileRepository.save(existingProfile);
            return existingProfileOptional.get();
        }


    }

    public void receiveProfile(ReceiveProfileDto receiveProfileDto, String recipientEmail) throws Exception {
        Profile senderProfile = profileRepository.findBySpecialId(receiveProfileDto.getSenderProfileSpecialId());

        // steps for sharing profile:
        // step 1: check if sender profile exists upon receiving it from the user
        if (senderProfile == null) {
            throw new Exception("Profile does not exist");
        }
        //        step 2: check if recipient account exists

        Account existingAccount = accountService.findByEmail(recipientEmail);//revert back

        if (existingAccount == null) {
            throw new Exception("Account with email: " + recipientEmail + " does not exist");
        }
        if (existingAccount.getId() == senderProfile.getAccount().getId()) {
            throw new Exception("Cannot send your own profile to yourself");
        }
//check if contact already exists
        Contact existingContact = contactService.findBySpecialId(receiveProfileDto.getSenderProfileSpecialId());
        if (existingContact != null) {
            throw new Exception("contact already exists");
        }

        //profile and account exists
        //        step 3: transfer profile and account details to contacts
        Contact contact = new Contact();
        contact.setSpecialId(senderProfile.getSpecialId());
        contact.setName(senderProfile.getName());
        contact.setLocation(receiveProfileDto.getLocation());
        contact.setAccount(existingAccount);

//      step 3: save contact

        contactService.saveContact(contact);


    }

    public void deleteByAccount(Account account) {
        profileRepository.deleteByAccount(account);
    }

    public void deleteAccountById(Long id) {
        profileRepository.deleteAccountById(id);
    }
}