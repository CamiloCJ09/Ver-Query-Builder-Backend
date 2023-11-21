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

    /**
     * Retrieves a list of CommentOutDTOs based on the provided user query ID.
     *
     * @param queryId The ID of the user query for which comments are to be retrieved.
     * @return A List of CommentOutDTOs representing the comments associated with the user query.
     *
     * @see CommentRepository#findAllByUserQuery_Id(UUID)
     * @see CommentMapper#toCommentOutDTO(Comment)
     * @see UUID#fromString(String)
     * @see List#stream()
     */
    public List<CommentOutDTO> getCommentByQueryId(String queryId) {
        return commentRepository.findAllByUserQuery_Id(UUID.fromString(queryId)).stream()
                .map(commentMapper::toCommentOutDTO).toList();
    }

    /**
     * Creates a new comment based on the provided CommentInDTO.
     *
     * @param commentInDTO The CommentInDTO containing information for creating the comment.
     * @return A CommentOutDTO representing the created comment.
     * @throws RuntimeException Thrown if the associated customer or user query is not found.
     *
     * @see CommentMapper#toComment(CommentInDTO)
     * @see CustomerRepository#findCostumerByUsername(String)
     * @see CustomerService#createCostumer(CustomerInDTO)
     * @see CommentRepository#save(Object)
     * @see CommentMapper#toCommentOutDTO(Comment)
     * @see UUID#fromString(String)
     *
     */
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
