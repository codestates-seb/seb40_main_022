import React from 'react';
import { useSelector } from 'react-redux';
import styled from 'styled-components';

const Paging = styled.div``;

// const PageNumber = styled.ul`
//   display: flex;
//   text-decoration: none;
//   list-style: none;
//   width: 1200px;
//   margin: 0 auto;
//   border: 1px solid red;
//   justify-content: center;
//   > li {
//     margin: 15px;
//   }
// `;
function PagiNation() {
  const [pages, setPages] = React.useState([]);
  const [size, setSize] = React.useState(10);
  const [totalElements] = React.useState(20);
  const [totalPages, setTotalPages] = React.useState(5);
  //  page
  //  size
  //  totalElements
  //  totalPages
  const items = useSelector(state => state.qnalist.pageInfo);
  const pers = [10, 20, 30];
  const pagination = Array(Math.ceil(totalPages / size));

  const setSizes = e => {
    setSize(e);
  };
  console.log(pers);
  console.log(items);
  console.log(pagination);
  console.log(totalPages);

  return (
    <>
      {pagination && items.length === 1 ? (
        pagination.map(item => (
          <>
            <h3>{item.totalElements}</h3>
            <h3>{item.totalPages}</h3>
            <h3>{item.page}</h3>
            <h3>{item.size}</h3>
          </>
        ))
      ) : (
        <h3>1</h3>
      )}
      <Paging
        page={setPages}
        pages={pages}
        totalPages={setTotalPages}
        totalElements={totalElements}
        size={setSizes}
      />
    </>
  );
}

export default PagiNation;
