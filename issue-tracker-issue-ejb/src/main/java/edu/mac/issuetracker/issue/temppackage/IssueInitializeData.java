/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.issue.temppackage;

import edu.mac.issuetracker.issue.data.IssueFacade;
import edu.mac.issuetracker.issue.data.TagColorFacade;
import edu.mac.issuetracker.issue.data.TagFacade;
import edu.mac.issuetracker.issue.entity.TagColor;
import edu.mac.issuetracker.issue.entity.Tag;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

/**
 *
 * @author E
 */
@Startup
@Singleton
public class IssueInitializeData {

    @Inject
    private IssueFacade issueFacade;

    @Inject
    private TagFacade tagFacade;

    @Inject
    private TagColorFacade tagColorFacade;

    @PostConstruct
    public void init() {
        
        if(tagFacade.count()<=0){
            //CREATE TAGS COLORS
            TagColor tagColor1 = new TagColor("Orange", "FFA500");
            tagColorFacade.create(tagColor1);
            TagColor tagColor2 = new TagColor("Red", "FF0000");
            tagColorFacade.create(tagColor2);
            tagColor2 = new TagColor("Violet", "EE82EE");
            tagColorFacade.create(tagColor2);
            tagColor2 = new TagColor("Blue", "0000FF");
            tagColorFacade.create(tagColor2);
            tagColor2 = new TagColor("Green", "008000");
            tagColorFacade.create(tagColor2);
            tagColor2 = new TagColor("Brown", "A52A2A");
            tagColorFacade.create(tagColor2);
            tagColor2 = new TagColor("Grey", "808080");
            tagColorFacade.create(tagColor2);

            //CREATE TAGS TITLES
            Tag tag = new Tag("Adware", tagColor1);
            tagFacade.create(tag);
            tag = new Tag("Backdoor", tagColor1);
            tagFacade.create(tag);
            tag = new Tag("Botnet", tagColor1);
            tagFacade.create(tag);
            tag = new Tag("Bug", tagColor1);
            tagFacade.create(tag);
            tag = new Tag("Cryptoware", tagColor1);
            tagFacade.create(tag);
            tag = new Tag("Cryptoworm", tagColor1);
            tagFacade.create(tag);
            tag = new Tag("Dropper", tagColor1);
            tagFacade.create(tag);
            tag = new Tag("Keylogger", tagColor1);
            tagFacade.create(tag);
            tag = new Tag("Ransomware", tagColor1);
            tagFacade.create(tag);
            tag = new Tag("Rootkit", tagColor1);
            tagFacade.create(tag);
            tag = new Tag("Scareware", tagColor1);
            tagFacade.create(tag);
            tag = new Tag("Spyware", tagColor2);
            tagFacade.create(tag);
            tag = new Tag("Trojan", tagColor2);
            tagFacade.create(tag);
            tag = new Tag("Virus", tagColor2);
            tagFacade.create(tag);
            tag = new Tag("Worm", tagColor2);
            tagFacade.create(tag);

        }
        /*//CREATE A TEST
        Map<String, Map<String, String>> issueDetailTag = new HashMap<>();
        Map<String, String> issueDetailTagProperties = new HashMap<>();
        issueDetailTagProperties.put("color", "fe1100");
        issueDetailTag.put("Trojan", issueDetailTagProperties);
        //Test test = new Test("Customer Email: 1906438E@student.tp.edu.sg", "medium", "not started", LocalDateTime.now().toString());
        //testFacade.create(test);

        //CREATE ISSUE
        IssueTag tag1 = new IssueTag("TagOne", "123456");
        IssueTag tag2 = new IssueTag("TagTwo", "654321");
        List<IssueTag> tagList = new ArrayList<IssueTag>();
        tagList.add(tag1);
        tagList.add(tag2);
        LocalDateTime endDate = LocalDateTime.parse("2022-01-13T21:30:00");
        IssueDetail issueDetail = new IssueDetail("Customer Email: 1906438E@student.tp.edu.sg", "Medium", tagList, endDate);
        IssueCustomerDetail issueCustomerDetail = new IssueCustomerDetail("Desktop22", "Windows 10", "Do not have", "jx", "jx@gmail.com", 11112222, "password");

        List<IssueDetail> issueDetailList = new LinkedList<IssueDetail>();
        List<IssueTeam> issueTeamList = new LinkedList<IssueTeam>();
        List<Long> collaboratorListAccountId = new ArrayList<Long>();
        collaboratorListAccountId.add(2L);
        collaboratorListAccountId.add(3L);
        IssueTeam team = new IssueTeam(1L, collaboratorListAccountId);
        issueDetailList.add(issueDetail);
        issueTeamList.add(team);
        Issue issue = new Issue(team, issueDetail, issueCustomerDetail, "1234", "this is the title", "description example");

        issueFacade.create(issue);

        //CREATE ISSUE
        tag1 = new IssueTag("TagOne", "123456");
        tag2 = new IssueTag("TagTwo", "654321");
        tagList = new ArrayList<IssueTag>();
        tagList.add(tag1);
        tagList.add(tag2);
        endDate = LocalDateTime.parse("2022-01-14T21:30:00");
        issueDetail = new IssueDetail("Customer Email: 1906438E@student.tp.edu.sg", "High", tagList, endDate);
        issueCustomerDetail = new IssueCustomerDetail("Desktop22", "Windows 10", "Do not have", "jx", "jx@gmail.com", 11112222, "password");

        issueDetailList = new LinkedList<IssueDetail>();
        issueTeamList = new LinkedList<IssueTeam>();
        collaboratorListAccountId = new ArrayList<Long>();
        collaboratorListAccountId.add(2L);
        collaboratorListAccountId.add(3L);
        team = new IssueTeam(1L, collaboratorListAccountId);
        issueDetailList.add(issueDetail);
        issueTeamList.add(team);
        issue = new Issue(team, issueDetail, issueCustomerDetail, "1234", "this is not the title", "description example");

        issueFacade.create(issue);

        //CREATE ISSUE
        tag1 = new IssueTag("TagOne", "123456");
        tag2 = new IssueTag("TagTwo", "654321");
        tagList = new ArrayList<IssueTag>();
        tagList.add(tag1);
        tagList.add(tag2);
        endDate = LocalDateTime.parse("2022-01-15T21:30:00");
        issueDetail = new IssueDetail("Customer Email: 1906438E@student.tp.edu.sg", "Low", tagList, endDate);
        issueCustomerDetail = new IssueCustomerDetail("Desktop22", "Windows 10", "Do not have", "jx", "jx@gmail.com", 11112222, "password");

        issueDetailList = new LinkedList<IssueDetail>();
        issueTeamList = new LinkedList<IssueTeam>();
        collaboratorListAccountId = new ArrayList<Long>();
        collaboratorListAccountId.add(2L);
        collaboratorListAccountId.add(3L);
        team = new IssueTeam(1L, collaboratorListAccountId);
        issueDetailList.add(issueDetail);
        issueTeamList.add(team);
        issue = new Issue(team, issueDetail, issueCustomerDetail, "1234", "this is not the title", "description example");

        issueFacade.create(issue);*/
    }
}
