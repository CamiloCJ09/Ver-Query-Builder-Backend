import CommentQueryType from "./CommentQueryType";

type QueryTableType = {

  id      : string
  queryname: string
  comment : string
  customer: string
  query   : string
  comments : CommentQueryType[]
}

export default QueryTableType