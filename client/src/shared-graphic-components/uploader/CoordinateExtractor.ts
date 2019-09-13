const CoordinateExtractor= (fileText: string): string => {
  const linedSeparatedBlob = fileText.split('\n');
  let threePartLines = linedSeparatedBlob.filter((line: string) => {
    return (
      line.split(' ').length === 3
    );
  });
  return threePartLines.join('\n');
};

export default CoordinateExtractor;