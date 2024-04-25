package entities;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private int id;
    private String email;
    private String[] roles ;
    private String password;
    private String firstName;
    private String lastName;
    private String profilePicture;
    private String jobTitle;
    private String professionalOverview;
    private String expertise;
    private int phone;
    private int rate;
    private List<String> language = new ArrayList<>();
    private String companyName;

    public User(String firstName, String lastName, String email, int phoneNumber, String companyName, String companyDescription) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phoneNumber;
        this.companyName = companyName;
        this.companyDescription = companyDescription;
    }
    public void setRolesAsJson(String rolesArray) {
        Gson gson = new Gson();
        this.roles = new String[]{gson.toJson(rolesArray)};
    }
    public User(int id, String email, String roles, String hashedPassword, String firstName, String lastName, String profilePicture, String jobTitle, String professionalOverview, String expertise, int phone, String companyName, String companyDescription, String industry, String companyWebsite, String companyLogo) {
        this.id = id;
        this.email = email;
        this.setRolesAsJson(roles);
        this.password = hashedPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
        this.jobTitle = jobTitle;
        this.professionalOverview = professionalOverview;
        this.expertise = expertise;
        this.phone = phone;
        this.companyName = companyName;
        this.companyDescription = companyDescription;
        this.industry = industry;
        this.companyWebsite = companyWebsite;
        this.companyLogo = companyLogo;
    }



    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    private String companyDescription;
    private String industry;
    private String companyWebsite;
    private String companyLogo;
    private String resetToken;
    private String lastLogin;

    public User(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.id = -1; // Default value for the ID, indicating it hasn't been set yet
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Constructor with ID parameter
    public User(int id, String email, String password, String firstName, String lastName) {
        this(email, password, firstName, lastName); // Call the other constructor to initialize common fields
        this.id = id; // Assign the provided ID
    }
    public User(int id, String email, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }



    public int getId() {
        return id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setFreelancerRole() {
        this.roles = new String[]{"ROLE_FREELANCER"};
    }

    public String getRolesArrayAsJson() {
        Gson gson = new Gson();
        return gson.toJson(roles);
    }

    public String getRolesAsJson() {
        // Use Gson to convert the roles array to JSON
        Gson gson = new Gson();
        return gson.toJson(roles);

    }

    public void setClientRole() {
        this.roles = new String[]{"ROLE_CLIENT"};
    }

    public void setAdminRole() {
        this.roles = new String[]{"ROLE_ADMIN"};
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getProfessionalOverview() {
        return professionalOverview;
    }

    public void setProfessionalOverview(String professionalOverview) {
        this.professionalOverview = professionalOverview;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public List<String> getLanguage() {
        return language;
    }

    public void setLanguage(List<String> language) {
        this.language = language;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public List<Integer> getFriendsList() {
        return friendsList;
    }

    public void setFriendsList(List<Integer> friendsList) {
        this.friendsList = friendsList;
    }

    private List<Integer> friendsList = new ArrayList<>();

    public User() {
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof User user)) return false;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", roles=" + this.getRolesAsJson() +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", professionalOverview='" + professionalOverview + '\'' +
                ", expertise='" + expertise + '\'' +
                ", phone=" + phone +
                ", rate=" + rate +
                ", language=" + language +
                ", companyName='" + companyName + '\'' +
                ", companyDescription='" + companyDescription + '\'' +
                ", industry='" + industry + '\'' +
                ", companyWebsite='" + companyWebsite + '\'' +
                ", companyLogo='" + companyLogo + '\'' +
                ", resetToken='" + resetToken + '\'' +
                ", lastLogin='" + lastLogin + '\'' +
                ", friendsList=" + friendsList +
                '}';
    }
public void setId(int id){
        this.id=id;
}
    public String getLanguageAsJson() {
        Gson gson = new Gson();
        return gson.toJson(language);
    }
    public String trimRoleString() {
        // Remove extra quotes and slashes from the role string
        return this.roles[0].replaceAll("\\\\\"", "").replaceAll("\"", "");
    }
    public void setPhoneNumber(String phoneNumber) {
        int parsedPhoneNumber = Integer.parseInt(phoneNumber);
        // Set the parsed phone number
        this.phone = parsedPhoneNumber;    }

    public String getPhoneNumber() {
        return String.valueOf(this.phone);
    }

}