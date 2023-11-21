import React, { useEffect } from "react";
import { useState } from "react";
import  bigQueryService  from "@/service/bigQueryService";
import { Input } from "@nextui-org/react";
import { Textarea } from "@nextui-org/react";
import {
  Modal,
  ModalContent,
  ModalHeader,
  ModalBody,
  ModalFooter,
  Button,
  useDisclosure,
} from "@nextui-org/react";
import GraphType from "@/types/GraphType";
import QuerySaveFormType from "@/types/QuerySaveFormType";
import { toast } from "react-hot-toast";

interface SaveQueryComProps {
  queryData: GraphType;
}

const queryFormSave: QuerySaveFormType = {
  queryname: "",
  comment: "",
  costumer: "",
  query: "",
  id: "",
  countryCode: "",
  seriesCode : "",
  year       : "",
  value      : "0"

};

const ModalSaveQuery = ({ queryData }: SaveQueryComProps) => {
  const { isOpen, onOpen, onOpenChange } = useDisclosure();
  const [queryForm, setQueryForm] = useState<QuerySaveFormType>(queryFormSave);

  useEffect(() => {
    const querySQL = queryData.query || "";
    setQueryForm((prevQueryForm) => ({
      ...prevQueryForm,
      query: querySQL,
      seriesCode: queryData.indicator,
      countryCode: queryData.countryCode,
      value: queryData.value,
      year: queryData.year,

    }));
  }, []);

  const handleQueryForm = (property: string, value: string) => {
    setQueryForm((prevQueryForm) => {
      let updatedQueryForm = { ...prevQueryForm, [property]: value };
      return updatedQueryForm;
    });
  };

  const handleSaveQuery = async () => {
    try {
      await bigQueryService.fetchSaveQuery(queryForm);
      toast.success("Query saved successfully");
    } catch (error) {
      toast.error("Error saving query");
    }
  };

  return (
    <>
      <Button onPress={onOpen} color="primary" className="w-[20%]">
        Save Query
      </Button>
      <Modal isOpen={isOpen} onOpenChange={onOpenChange}>
        <ModalContent>
          {(onClose) => (
            <>
              <ModalHeader className="flex flex-col gap-1">
                Storage your query!!
              </ModalHeader>
              <ModalBody>
                <p>Complete the fields to save your query.
                It doesn&apos;t matter if you haven&apos;t created an account, just enter the name under which you want to save the query
                </p>
                <Input
                  value={queryForm.costumer}
                  onChange={(value) =>
                    handleQueryForm("costumer", value.target.value)
                  }
                  type="text"
                  label="User Name"
                  placeholder="Enter your username"
                />
                <Input
                  value={queryForm.queryname}
                  onChange={(value) =>
                    handleQueryForm("queryname", value.target.value)
                  }
                  type="text"
                  label="Name Query"
                  placeholder="Enter the name of the query"
                />
                <Textarea
                  value={queryForm.comment}
                  label="Description"
                  placeholder="Enter your description"
                  onChange={(value) =>
                    handleQueryForm("comment", value.target.value)
                  }
                />
              </ModalBody>
              <ModalFooter>
                <Button color="danger" variant="light" onPress={onClose}>
                  Close
                </Button>
                <Button
                  color="primary"
                  onPress={onClose}
                  onClick={handleSaveQuery}
                >
                  Save Query
                </Button>
              </ModalFooter>
            </>
          )}
        </ModalContent>
      </Modal>
    </>
  );
};

export default ModalSaveQuery;