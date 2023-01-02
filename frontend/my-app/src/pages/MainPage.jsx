import { ThemeProvider } from "@mui/material";
import { Theme } from "../Theme/Theme";
import MenuAppBarComponent from "../components/appbar/MenuAppBarComponent";
import {
  LINK_BASKETITEM_PAGE,
  LINK_STARTPAGE,
} from "../globalvariables/LinkUrl";
import StartPage from "./StartPage";
import BasketItemPage from "./BasketItemPage";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";

const MainPage = () => {
  return (
    <>
      <ThemeProvider theme={Theme}>
        <MenuAppBarComponent />
        <Router>
          <Routes>
            <Route path={LINK_STARTPAGE} element={<StartPage />} />
            <Route path={LINK_BASKETITEM_PAGE} element={<BasketItemPage />} />
          </Routes>
        </Router>
      </ThemeProvider>
    </>
  );
};

export default MainPage;
