package com.ver.QueryBuilder.service;

import com.ver.QueryBuilder.dto.request.CommentInDTO;
import com.ver.QueryBuilder.dto.request.CustomerInDTO;
import com.ver.QueryBuilder.dto.response.CommentOutDTO;
import com.ver.QueryBuilder.mapper.CommentMapper;
import com.ver.QueryBuilder.mapper.CustomerMapper;
import com.ver.QueryBuilder.model.ownEntites.Comment;
import com.ver.QueryBuilder.model.ownEntites.Customer;
import com.ver.QueryBuilder.repository.CommentRepository;
import com.ver.QueryBuilder.repository.CustomerRepository;
import com.ver.QueryBuilder.repository.UserQueryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentMapper commentMapper;
    private final CustomerRepository customerRepository;
    private final UserQueryRepository userQueryRepository;
    private final CommentRepository commentRepository;
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    public List<CommentOutDTO> getCommentByQueryId(String queryId) {
        return commentRepository.findAllByUserQuery_Id(UUID.fromString(queryId)).stream()
                .map(commentMapper::toCommentOutDTO).toList();
    }

    public CommentOutDTO createComment(CommentInDTO commentInDTO) {
        Comment comment = commentMapper.toComment(commentInDTO);
        Customer customer = customerRepository.findCostumerByUsername(commentInDTO.getCostumerId())
                .orElse(null);
        if (customer == null) {
            customer = customerMapper.toCustomer(customerService.createCostumer(CustomerInDTO.builder().username(commentInDTO.getCostumerId()).build()));
        }
        comment.setCustomer(customerRepository.findCostumerByUsername(customer.getUsername())
                .orElseThrow(() -> new RuntimeException("Customer not found")));
        comment.setUserQuery(userQueryRepository.findById(UUID.fromString(commentInDTO.getUserQueryId()))
                .orElseThrow(() -> new RuntimeException("UserQuery not found")));
        return commentMapper.toCommentOutDTO(commentRepository.save(comment));
    }

}
