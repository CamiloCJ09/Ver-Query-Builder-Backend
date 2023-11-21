import React, { useState, useEffect } from "react";
import {
  Modal,
  ModalContent,
  ModalHeader,
  ModalBody,
  ModalFooter,
  Button,
  useDisclosure,
  CircularProgress,
} from "@nextui-org/react";
import QuerySaveFormType from "@/types/QuerySaveFormType";
import { toast } from "react-hot-toast";
import QueryToShow from "@/types/QueryToShow";

import bigQueryService from "@/service/bigQueryService";
import GraphType from "@/types/GraphType";
import CommentsComponent from "../comments/CommentsComponent";
import ChartComponent from "../charts/chart";

interface QueryDetailProps {
  query: QuerySaveFormType;
}

const QueryTableDetail = ({ query }: QueryDetailProps) => {
  const [loaded, setLoaded] = useState<boolean>(false);
  const [queryData, setQueryData] = useState<GraphType | null>(null);
  const { isOpen, onOpen, onOpenChange } = useDisclosure();

  const fetchDataSelect = async () => {
    try {
      const dataToSend: QueryToShow = {
        query: query.query,
      };
      const response = await bigQueryService.executeSelectedQuery(dataToSend);
      setLoaded(true);

      if (response) setQueryData(response);
      toast.success("Query get successfully");
    } catch (error) {
      toast.error("Error fetching queries");
    }
  };

  useEffect(() => {
    if (isOpen && !queryData) fetchDataSelect();
  }, [isOpen]);

  return (
    <>
      <Button onPress={onOpen} size="sm" color="primary">
        Details
      </Button>

      <Modal
        size="5xl"
        isOpen={isOpen}
        onOpenChange={onOpenChange}
        scrollBehavior={"outside"}
      >
        <ModalContent>
          {(onClose) => (
            <>
              <ModalHeader className="flex flex-col gap-1">
                View Query
              </ModalHeader>
              <ModalBody>
                {!loaded ? (
                  <div className="flex items-center justify-center h-[100%]">
                    <CircularProgress label="Loading" size="lg" />
                  </div>
                ) : (
                  queryData && (
                    <div>
                      <div className="text-large">
                        <div className="flex flex-col text-center justify-around">
                          <div>
                            Nombre de la Query:{" "}
                            <span className="font-bold">{query.queryname}</span>
                          </div>
                          <div className="text-sm">
                            Creador:{" "}
                            <span className="font-bold">
                              {query.costumer}
                            </span>
                          </div>
                        </div>
                        <div>
                          <div className="flex flex-col justify-center mt-5 w-[100%] text-center  ">
                            <span className="font-bold">Descripción:</span>{" "}
                            <span>{query.comment}</span>
                          </div>
                        </div>
                      </div>
                      <ChartComponent queryData={queryData} />
                      <CommentsComponent queryId={query.id.toString()} />
                    </div>
                  )
                )}
              </ModalBody>
              <ModalFooter>
                <Button color="danger" variant="light" onPress={onClose}>
                  Close
                </Button>
              </ModalFooter>
            </>
          )}
        </ModalContent>
      </Modal>
    </>
  );
};

export default QueryTableDetail;