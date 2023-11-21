import CommentQueryType from "@/types/CommentQueryType";
import React, { useState, useEffect } from "react";
import { toast } from "react-hot-toast";
import bigQueryService from "@/service/bigQueryService";
import { Button } from "@nextui-org/button";
import NewComment from "./NewComment";
import CommentFormType from "@/types/CommentFormType";
import CommentCard from "./CommentCard";

interface MainCommentProps {
  queryId: string;
}

const CommentsComponent = ({ queryId }: MainCommentProps) => {
  const [comments, setComments] = useState<CommentQueryType[]>([]);
  const [isCommentCreating, setIsCommentCreating] = useState<boolean>(false);

  const fetchGetComments = async () => {
    try {
      const response = await bigQueryService.fetchGetCommentByQuery(queryId);
      if (response) setComments(response);
    } catch (error) {
      toast.error("Error fetching comments");
    }
  };

  const fetchOnCreateComment = async (commentText: string, customerId: string) => {
    try {
      const commentToSend: CommentFormType = {
        comment: commentText,
        userQueryId: queryId,
        costumerId: customerId,
      };
      const response = await bigQueryService.fetchCreateComment(commentToSend);
      if (response) {
        toast.success("Comment created successfully");
        fetchGetComments();
      }
    } catch (error) {
      toast.error("Error creating comment");
    }
  };

  useEffect(() => {
    fetchGetComments();
  }, []);

  return (
    <div className="ml-12">
      {!isCommentCreating ? (
        <div>
          <Button
            color="primary"
            onClick={() => setIsCommentCreating(!isCommentCreating)}
          >
            Add Comment
          </Button>
        </div>
      ) : (
        <NewComment
          isCommentCreating={isCommentCreating}
          setIsCommentCreating={setIsCommentCreating}
          fetchOnCreateComment={fetchOnCreateComment}
        />
      )}
      {comments.length === 0 ? (
        <div className="ml-1 mt-4">
          <div className="font-bold">
            No comments yet, be the first to comment.
            Tell us what you think!!
          </div>
        </div>
      ) : (
        <div className="flex flex-col w-full mt-4 gap-4">
          {comments
            .slice()
            .reverse()
            .map((comment, index) => (
              <CommentCard comment={comment} key={index} />
            ))}
        </div>
      )}
    </div>
  );
};

export default CommentsComponent;