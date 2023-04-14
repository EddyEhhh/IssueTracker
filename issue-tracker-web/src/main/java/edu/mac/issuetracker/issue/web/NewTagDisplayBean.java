/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.issue.web;

import edu.mac.issuetracker.issue.dto.IssueTagDTO;
import edu.mac.issuetracker.issue.dto.TagColorDTO;
import edu.mac.issuetracker.issue.dto.TagDTO;
import edu.mac.issuetracker.issue.manager.IssueManager;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Sam
 */
@Named
@ViewScoped
public class NewTagDisplayBean implements Serializable {

    @EJB
    private IssueManager issueManager;

    // user input
    @NotBlank(message = "Field cannot be empty")
    private String tagTitle;

    @NotBlank(message = "Field cannot be empty")
    private String tagColor;

    // for input
    private IssueTagDTO tag;

    private List<IssueTagDTO> tagList = new ArrayList<>();;

    // from db
    private List<String> tagTitleList = new ArrayList<>();

    private Map<String, String> tagColorList = new HashMap<>();

    private Map<String, Map<String, String>> tagMap = new HashMap<>();

    private Map<String, String> colorMap = new HashMap<>();

    @PostConstruct
    public void init() {
        // put tag color name and tag hexcode into colorMap (hash map)
        List<TagColorDTO> tagColorDTOList = issueManager.getAllTagColor();
        for (TagColorDTO tagColorDTO : tagColorDTOList) {
            colorMap.put(tagColorDTO.getColorName(), "tag-" + tagColorDTO.getHexColor());
        }

        // put tag title and the related color name and hexcode into tagMap (hash map)
        List<TagDTO> tagDTOList = issueManager.getAllTag();
        for (TagDTO tagDTO : tagDTOList) {
            Map<String, String> map = new HashMap<>();
            map.put(tagDTO.getTagColor().getColorName(), "tag-" + tagDTO.getTagColor().getHexColor());
            tagMap.put(tagDTO.getTitle(), map);
        }

        // get tagMap key which is the tag title into tagTitleList
        for (String title : tagMap.keySet()) {
            tagTitleList.add(title);
        }
    }

    // get the related tag title color on dropdown display
    public void handleChange() {
        if (tagTitle != null && tagTitleList.contains(tagTitle)) {
            tagColorList = tagMap.get(tagTitle);
        } else {
            tagColorList = colorMap;
        }
    }

    public void updateTag() {
        tag = new IssueTagDTO();
        tag.setTitle(tagTitle);
        tag.setHexColor(tagColor);
        tagList.add(tag);
    }

    public void removeTag() {
        this.tagList.remove(this.tag);
        this.tag = null;
        PrimeFaces.current().ajax().update("form:display");
    }

    public String getTagTitle() {
        return tagTitle;
    }

    public void setTagTitle(String tagTitle) {
        this.tagTitle = tagTitle;
    }

    public String getTagColor() {
        return tagColor;
    }

    public void setTagColor(String tagColor) {
        this.tagColor = tagColor;
    }

    public IssueTagDTO getTag() {
        return tag;
    }

    public void setTag(IssueTagDTO tag) {
        this.tag = tag;
    }

    public List<IssueTagDTO> getTagList() {
        return tagList;
    }

    public void setTagList(List<IssueTagDTO> tagList) {
        this.tagList = tagList;
    }

    public Map<String, Map<String, String>> getTagMap() {
        return tagMap;
    }

    public void setTagMap(Map<String, Map<String, String>> tagMap) {
        this.tagMap = tagMap;
    }

    public Map<String, String> getTagColorList() {
        return tagColorList;
    }

    public void setTagColorMap(Map<String, String> tagColorList) {
        this.tagColorList = tagColorList;
    }

    public List<String> getTagTitleList() {
        return tagTitleList;
    }

    public void setTagTitleList(List<String> tagTitleList) {
        this.tagTitleList = tagTitleList;
    }
}
