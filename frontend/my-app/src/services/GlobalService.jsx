import axios from "axios";

const getAllApiObjects = async () => {
  let response = await axios.get("http://localhost:8080/api/item");
  return await response.data;
};

const exports = {
  getAllApiObjects,
};
export default exports;
