import React from "react";

import {
  Table,
  TableHeader,
  TableColumn,
  TableBody,
  TableRow,
  TableCell,
  Tooltip,
  Button,
} from "@nextui-org/react";
import QueryTableType from "@/types/QueryTableType";
import QueryTableDetail from "./QueryTableDetail";
import QuerySaveFormType from "@/types/QuerySaveFormType";


interface QueryTableProps {
  queries: QuerySaveFormType[];
}
const QueryTable = ({ queries }: QueryTableProps) => {
  return (
    <Table aria-label="Example static collection table">
      <TableHeader>
        <TableColumn>Query Name</TableColumn>
        <TableColumn>User</TableColumn>
        <TableColumn>World</TableColumn>
        <TableColumn>Description</TableColumn>
        <TableColumn>Action</TableColumn>
      </TableHeader>
      <TableBody>
        {queries.map((query, index) => (
          <TableRow key={index}>
            <TableCell>{query.queryname}</TableCell>
            <TableCell>{query.costumer}</TableCell>
            <TableCell>{query.queryname}</TableCell>
            <TableCell>{query.comment}</TableCell>
            <TableCell>
              <QueryTableDetail query={query} />
            </TableCell>
          </TableRow>
        ))}
      </TableBody>
    </Table>
  );
}

export default QueryTable;