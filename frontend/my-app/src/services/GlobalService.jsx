import axios from "axios";

const getAllApiObjects = async () => {
  let response = await axios.get("http://localhost:8080/api/item");
  return await response.data;
};

const deleteApiObject = async (itemNumber) => {
  return await axios.delete(
    "http://localhost:8080/api/item" + "/" + itemNumber
  );
};

const postApiObject = async (objectApi) => {
  let response = await axios.post("http://localhost:8080/api/item", objectApi);
  return await response.data;
};

const exports = {
  getAllApiObjects,
  deleteApiObject,
  postApiObject,
};
export default exports;
