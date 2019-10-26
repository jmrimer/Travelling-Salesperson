import React, { useCallback } from 'react';
import { useDropzone } from 'react-dropzone';
import styled from 'styled-components';

const MapFileDropdown = (props: any) => {
  const onDrop = useCallback(acceptedFiles => {
    const reader = new FileReader();

    reader.onabort = () => console.log('file reading was aborted');
    reader.onerror = () => console.log('file reading failed');
    reader.onload = () => {
      const binaryStr = reader.result;
      if (typeof binaryStr === 'string') {
        props.fileHandler(binaryStr);
      }
    };

    acceptedFiles.forEach((file: Blob) => {
      reader.readAsBinaryString(file);
    });
  }, [props]);

  const {getRootProps, getInputProps} = useDropzone({onDrop});

  return (
    <div className={props.className} {...getRootProps()}>
      <input {...getInputProps()}/>
      <div>Upload TSP coordinate files</div>
      <div>(drag 'n' drop or click to browse)</div>
    </div>
  );
};

export const StyledMapFileDropzone = styled(MapFileDropdown)`
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  background: ${(props) => props.theme.color.lavender};
  height: 80px;
  border: 1px solid ${(props) => props.theme.color.fontWhite};
  color: ${(props) => props.theme.color.fontWhite}
  border-radius: 4px;
  opacity: 0.75;
  padding: 16px;
  text-align: center;
  cursor: pointer;
  
`;