import React, { useState, useEffect } from "react"
import { Button, Textarea } from "@nextui-org/react"
import { Input } from "@nextui-org/react"

interface NewCommentProps {
  isCommentCreating: boolean
  setIsCommentCreating: (value: boolean) => void
  fetchOnCreateComment: (commentText: string, customerId: string) => void
}

const NewComment = ({
  isCommentCreating,
  setIsCommentCreating,
  fetchOnCreateComment,
}: NewCommentProps) => {
  const [commentText, setCommentText] = useState<string>("")
  const [userName, setUserName] = useState<string>("")

  const handleCommentText = (value: string) => {
    setCommentText(value)
  }
  const handleUserName = (value: string) => {
    setUserName(value)
  }

  const onSubmitComment = () => {
    setIsCommentCreating(!isCommentCreating)
    fetchOnCreateComment(commentText, userName)
  }
  return (
    <div className="flex items-center justify-between w-full">
      <div className="flex-grow pr-4 ">
        <div className="pb-3 pt-4">
          <Input
            value={userName}
            onChange={(value) => handleUserName(value.target.value)}
            type="text"
            label="Enter username"
            placeholder="Enter the username"
          />
        </div>

        <div>
          <Textarea
            value={commentText}
            label="Comment"
            placeholder="Enter your comment"
            className="w-full"
            onChange={(e) => handleCommentText(e.target.value)}
          />
        </div>
      </div>
      <div>
        <Button color="primary" onClick={onSubmitComment}>
          Submit
        </Button>
      </div>
    </div>
  )
}

export default NewComment
