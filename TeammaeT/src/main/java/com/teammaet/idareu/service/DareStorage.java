package com.teammaet.idareu.service;

import com.teammaet.idareu.model.Dare;
import com.teammaet.idareu.model.DareType;
import com.teammaet.idareu.repository.DareRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DareStorage {

    @Autowired
    private DareRepository dareRepository;

    private Logger logger = LoggerFactory.getLogger(DareStorage.class);

    public void save(Dare dare) {
            dareRepository.save(dare);
    }

    public Dare getDareBy(Long id) throws NullPointerException {
        return dareRepository.findById(id).orElseThrow(() -> {
            NullPointerException e = new NullPointerException("User not found.");
            logger.info(e.getMessage());
            throw e;
        });
    }

    public void delete(Long dareId) {
        dareRepository.delete(getDareBy(dareId));
    }

    public List<Dare> getDares(Long userId, DareType dareType) {
        return dareRepository.findAllByUserIdAndDareType(userId, dareType);
    }

    public List<Dare> getAllDare(Long userId) {
        return dareRepository.findAllByUserId(userId);
    }
}
