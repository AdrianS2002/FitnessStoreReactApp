import {Form} from "react-bootstrap";
import {useTranslation} from "react-i18next";


const LanguagePicker = () => {
    const { i18n } = useTranslation();

    const handleLangChange = (evt) => {
        const lang = evt.target.value;
        localStorage.setItem("lang", lang);
        i18n.changeLanguage(lang);
    };

    return (
        <Form.Select
            aria-label='Default select example'
            onChange={handleLangChange}
            value={localStorage.getItem("lang")}
        >
            <option value='en'>EN</option>
            <option value='ro'>RO</option> 
        </Form.Select>
    );
}

export default LanguagePicker;