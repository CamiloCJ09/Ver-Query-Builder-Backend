"use client";

import QuerySaveFormType from "@/types/QuerySaveFormType";
import React, { useState, useEffect } from "react";
import { toast } from "react-hot-toast";
import bigQueryService from "@/service/bigQueryService";
import QueryTable from "@/components/queryTable/QueryTable";

const QueriesPage = () => {
  const [queries, setQueries] = useState<QuerySaveFormType[]>([]);

  const fetchDataAllQueries = async () => {
    try {
      const response = await bigQueryService.fetchAllQueries();
      setQueries(response);
    } catch (error) {
      toast.error("Error fetching queries");
    }
  };

  useEffect(() => {
    fetchDataAllQueries();
  }, []);

  return (
    <div>
      {queries.length === 0 ? (
        <div className="text-centr font-bold text-2xl mt-10">
          There are no queries saved
        </div>
      ) : (
        <QueryTable queries={queries} />
      )}
    </div>
  );
};

export default QueriesPage;