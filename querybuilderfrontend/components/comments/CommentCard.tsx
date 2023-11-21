import React, { useState, useEffect } from "react"
import {
  Card,
  CardHeader,
  CardBody,
  CardFooter,
  Divider,
  Link,
  Avatar,
} from "@nextui-org/react"
import CommentQueryType from "@/types/CommentQueryType"

interface CardCommentProps {
  comment: CommentQueryType
}

const CommentCard = ({ comment }: CardCommentProps) => {
  
  return (
    <Card>
      <CardHeader className="flex gap-3">
        <Avatar showFallback src="https://images.unsplash.com/broken" />
        <div className="flex flex-col">
          <p className="text-md">{comment.userQueryId}</p>
          <p className="text-small text-default-500">{comment.costumerId}</p>
        </div>
      </CardHeader>
      <Divider />
      <CardBody>
        <p>{comment.comment}</p>
      </CardBody>
      <Divider />
    </Card>
  )
}

export default CommentCard
