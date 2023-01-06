import { CssBaseline, ThemeProvider } from "@mui/material";
import {
  LINK_BASKETITEM_PAGE,
  LINK_STARTPAGE,
} from "../globalvariables/LinkUrl";
import StartPage from "./StartPage";
import BasketItemPage from "./BasketItemPage";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import { ColorModeContext, useMode } from "../Theme/Theme";
import MenuComponent from "../components/appbar/MenuComponent";

const MainPage = () => {
  const [theme, colorMode] = useMode();

  return (
    <>
      <ColorModeContext.Provider value={colorMode}>
        <CssBaseline />
        <ThemeProvider theme={theme}>
          <Router>
            <MenuComponent />
            <Routes>
              <Route path={LINK_STARTPAGE} element={<StartPage />} />
              <Route path={LINK_BASKETITEM_PAGE} element={<BasketItemPage />} />
            </Routes>
          </Router>
        </ThemeProvider>
      </ColorModeContext.Provider>
    </>
  );
};

export default MainPage;
