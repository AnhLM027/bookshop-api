package ptit.edu.vn.bookshop.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ptit.edu.vn.bookshop.domain.constant.StatusEnum;
import ptit.edu.vn.bookshop.domain.dto.request.PermissionCreateRequestDTO;
import ptit.edu.vn.bookshop.domain.dto.request.PermissionUpdateRequestDTO;
import ptit.edu.vn.bookshop.domain.dto.response.page.PermissionPageResponseDTO;
import ptit.edu.vn.bookshop.domain.dto.response.PermissionResponseDTO;
import ptit.edu.vn.bookshop.domain.entity.Permission;
import ptit.edu.vn.bookshop.exception.IdInvalidException;
import ptit.edu.vn.bookshop.repository.PermissionRepository;
import ptit.edu.vn.bookshop.repository.specification.PermissionSpecificationBuilder;
import ptit.edu.vn.bookshop.service.PermissionService;
import ptit.edu.vn.bookshop.service.mapper.PermissionMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissonMapper;

    public PermissionServiceImpl(PermissionRepository permissionRepository, PermissionMapper permissionMapper) {
        this.permissionRepository = permissionRepository;
        this.permissonMapper = permissionMapper;
    }

    @Override
    public PermissionResponseDTO createPermission(PermissionCreateRequestDTO permissionRequestDTO) {
        if (permissionRequestDTO.getApiPath() != null && !permissionRequestDTO.getApiPath().isEmpty()
                && permissionRequestDTO.getModule() != null && !permissionRequestDTO.getModule().isEmpty()
                && permissionRequestDTO.getMethod() != null && !permissionRequestDTO.getMethod().isEmpty()
                && this.permissionRepository.existsByModuleAndApiPathAndMethod(permissionRequestDTO.getModule(), permissionRequestDTO.getApiPath(), permissionRequestDTO.getMethod())) {
            throw new DataIntegrityViolationException("Permission already exists with same module, apiPath and method");
        }
        Permission permission = this.permissonMapper.mapperPermissionRequestDtoToPerMission(permissionRequestDTO);
        permission.setStatus(StatusEnum.ACTIVE);
        return this.permissonMapper.mapperPermissionToPermissionResponse(this.permissionRepository.save(permission));
    }

    @Override
    public PermissionResponseDTO updatePermission(PermissionUpdateRequestDTO permissionRequestDTO, Long id) {
        Optional<Permission> permissionOptional = this.permissionRepository.findById(id);
        if (!permissionOptional.isPresent()) {
            throw new IdInvalidException("Permission Id is invalid");
        }

        Permission permission = permissionOptional.get();
        if (permissionRequestDTO.getName() != null) permission.setName(permissionRequestDTO.getName());
        if (permissionRequestDTO.getApiPath() != null) permission.setApiPath(permissionRequestDTO.getApiPath());
        if (permissionRequestDTO.getMethod() != null) permission.setMethod(permissionRequestDTO.getMethod());
        if (permissionRequestDTO.getModule() != null) permission.setModule(permissionRequestDTO.getModule());
        if (permissionRequestDTO.getStatus() != null) permission.setStatus(permissionRequestDTO.getStatus());
        return this.permissonMapper.mapperPermissionToPermissionResponse(this.permissionRepository.save(permission));
    }

    @Override
    public void deletePermission(Long id) {
        Optional<Permission> permissionOptional = this.permissionRepository.findById(id);
        if (!permissionOptional.isPresent()) {
            throw new IdInvalidException("Permission Id is invalid");
        }
        Permission permission = permissionOptional.get();
        permission.setStatus(StatusEnum.INACTIVE);
        this.permissionRepository.save(permission);
    }


    @Override
    public PermissionResponseDTO fetchPermission(Long id) {
        Optional<Permission> permissionOptional = this.permissionRepository.findById(id);
        if (!permissionOptional.isPresent()) {
            throw new IdInvalidException("Permission Id is invalid");
        }
        Permission permission = permissionOptional.get();
        return this.permissonMapper.mapperPermissionToPermissionResponse(permission);
    }

    @Override
    public PermissionPageResponseDTO getAllPermission(Pageable pageable, String[] permission) {
        Page<Permission> permissionPage;
        if (permission != null && permission.length > 0) {
            PermissionSpecificationBuilder builder = new PermissionSpecificationBuilder();
            for (String p : permission) {
                Pattern pattern = Pattern.compile("(\\w+?)([:<>~!])(.*)(\\p{Punct}?)(.*)(\\p{Punct}?)");
                Matcher matcher = pattern.matcher(p);
                if (matcher.find()) {
                    builder.with(
                            matcher.group(1),
                            matcher.group(2),
                            matcher.group(3),
                            matcher.group(4),
                            matcher.group(5));
                }
            }
            permissionPage = this.permissionRepository.findAll(builder.build(), pageable);
        } else {
            permissionPage = this.permissionRepository.findAll(pageable);
        }
        PermissionPageResponseDTO responseDTO = new PermissionPageResponseDTO();
        responseDTO.setPage(pageable.getPageNumber() + 1);
        responseDTO.setPageSize(pageable.getPageSize());
        responseDTO.setTotal(permissionPage.getTotalElements());
        responseDTO.setPages(permissionPage.getTotalPages());
        responseDTO.setPermissions(permissionPage.stream().map(permissonMapper::mapperPermissionToPermissionResponse).collect(Collectors.toList()));
        return responseDTO;
    }
}
