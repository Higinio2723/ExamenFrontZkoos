package com.front.zkoos.module.service;


import com.front.zkoos.module.entities.Language;
import com.front.zkoos.module.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yann39
 * @since nov 2018
 */
@Transactional
@Component("languageMgr")
public class LanguageManager {

    @Autowired
    private LanguageRepository languageRepository;

    /**
     * Get all countries from the database
     *
     * @return A list of Country elements representing the countries
     */
    public List<Language> getAll() {
        return (List<Language>) languageRepository.findAll();
    }

}