import React, { useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import {
  fetchItems,
  chaneItemName,
  outputState,
} from "../features/basketItem/basketItemSlice";

const StartPage = () => {
  const items = useSelector((state) => state.items);
  const dispatch = useDispatch();
  console.log(items);

  useEffect(() => {
    dispatch(fetchItems());
  }, []);
  return (
    <>
      <h1>List of Items</h1>
      {items.loading && <div>Loading...</div>}
      {!items.loading && items.error ? <div>Error: {items.error}</div> : null}
      <button onClick={() => dispatch(chaneItemName("hallo2"))}>change</button>
      <button onClick={() => dispatch(outputState({ id: 111, name: "sas" }))}>
        show show
      </button>
    </>
  );
};

export default StartPage;
