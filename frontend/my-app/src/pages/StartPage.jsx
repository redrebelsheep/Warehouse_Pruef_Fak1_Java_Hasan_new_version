import React from "react";
import { useSelector, useDispatch } from "react-redux";
import { outputState, postItem } from "../features/basketItem/basketItemSlice";
import GlobalService from "../services/GlobalService";

const StartPage = () => {
  const items = useSelector((state) => state.items);
  const dispatch = useDispatch();
  console.log(items);

  const textObj = {
    productName: "Post5",
    purchasingPrice: 2.2,
    sellingPrice: 1.0,
    manufacturer: "Post4",
    amount: 0,
  };

  const testpost2 = (obj) => {
    fetch("http://localhost:8080/api/item", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify(textObj),
    });
  };

  const testpost = (obj) => {
    console.log(obj);
    GlobalService.postApiObject(obj);
  };

  return (
    <>
      <h1>List of Items</h1>
      {items.loading && <div>Loading...</div>}
      {!items.loading && items.error ? <div>Error: {items.error}</div> : null}
      <button onClick={() => dispatch(outputState({ id: 111, name: "sas" }))}>
        show show
      </button>
      <button onClick={() => testpost(textObj)}>save Test</button>
    </>
  );
};

export default StartPage;
