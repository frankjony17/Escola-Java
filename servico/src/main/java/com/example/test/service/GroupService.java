package com.example.test.service;

import com.example.test.model.Group;
import com.example.test.model.dto.GroupDTO;
import com.example.test.repository.GroupRepository;
import com.example.test.util.MapperUtils;
import com.example.test.util.Resposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public Resposta<List<GroupDTO>> list(int offset, int limit) {
        List<Group> groupList = groupRepository.findAllPaginado(offset, limit);
        Resposta<List<GroupDTO>> resposta = new Resposta<>();
        resposta.setResult(MapperUtils.map(groupList, GroupDTO.class));
        resposta.setTotalElements(groupRepository.countAll());
        return resposta;
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public void save(GroupDTO groupDTO) {
        Group group = new Group();
        group.setNome(groupDTO.getNome());
        group.setLocation(groupDTO.getLocation());
        groupRepository.save(group);
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public void edit(GroupDTO groupDTO) {
        Group group = groupRepository.findById(groupDTO.getId()).get();
        group.setNome(groupDTO.getNome());
        group.setLocation(groupDTO.getLocation());
        groupRepository.save(group);
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public void remove(Long id) {
        if (id != null) {
            groupRepository.delete(groupRepository.findById(id).get());
        }
    }
}
