import MainPage from "./pages/MainPage";
import { useSelector, useDispatch } from "react-redux";
import { fetchItems } from "./features/basketItem/basketItemSlice";
import { useEffect } from "react";

const App = () => {
  const items = useSelector((state) => state.items);
  const dispatch = useDispatch();
  console.log(items);

  useEffect(() => {
    dispatch(fetchItems());
  }, []);

  return (
    <>
      <MainPage />
    </>
  );
};

export default App;
