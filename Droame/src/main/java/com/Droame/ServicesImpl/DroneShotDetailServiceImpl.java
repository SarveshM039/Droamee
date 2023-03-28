package com.Droame.ServicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Droame.Exception.NotFoundException;
import com.Droame.Repositrory.DroneShotDetailRepository;
import com.Droame.Services.DroneShotDetailService;
import com.Droame.entities.DroneShotDetail;

@Service
public class DroneShotDetailServiceImpl implements DroneShotDetailService {

    @Autowired
    private DroneShotDetailRepository droneShotDetailRepository;

    @Override
    public List<DroneShotDetail> getAllDroneShotDetails() {
        return droneShotDetailRepository.findAll();
    }

    @Override
    public DroneShotDetail getDroneShotDetailById(Long id) throws NotFoundException {
        Optional<DroneShotDetail> droneShotDetail = droneShotDetailRepository.findById(id);
        if (droneShotDetail.isEmpty()) {
            throw new NotFoundException("DroneShotDetail not found with id: " + id);
        }
        return droneShotDetail.get();
    }

    @Override
    public void saveDroneShotDetail(DroneShotDetail droneShotDetail) {
        droneShotDetailRepository.save(droneShotDetail);
    }

    @Override
    public void updateDroneShotDetail(Long id, DroneShotDetail droneShotDetail) throws NotFoundException {
        Optional<DroneShotDetail> existingDroneShotDetail = droneShotDetailRepository.findById(id);
        if (existingDroneShotDetail.isEmpty()) {
            throw new NotFoundException("DroneShotDetail not found with id: " + id);
        }
        DroneShotDetail updatedDroneShotDetail = existingDroneShotDetail.get();
        updatedDroneShotDetail.setName(droneShotDetail.getName());
        updatedDroneShotDetail.setDescription(droneShotDetail.getDescription());
        droneShotDetailRepository.save(updatedDroneShotDetail);
    }

    @Override
    public void deleteDroneShotDetail(Long id) throws NotFoundException {
        Optional<DroneShotDetail> droneShotDetail = droneShotDetailRepository.findById(id);
        if (droneShotDetail.isEmpty()) {
            throw new NotFoundException("DroneShotDetail not found with id: " + id);
        }
        droneShotDetailRepository.delete(droneShotDetail.get());
    }
}
