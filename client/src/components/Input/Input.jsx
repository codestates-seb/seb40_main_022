import styled from 'styled-components';

const InBox = styled.input``;

function Input({ Intext, value, setValue, type }) {
  return (
    <InBox
      placeholder={Intext}
      value={value}
      onChange={e => {
        setValue(e.target.value);
      }}
      type={type}
    />
  );
}

export default Input;
