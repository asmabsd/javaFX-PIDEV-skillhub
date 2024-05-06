package entities;


/*public class PDFBoxDocument {



    /**
     * An implementation of {@link Document} for the Apache PDFBox library.
     *
     * @see PDFView#setDocument(Document)
     */
   /* public class PDFBoxDocument  {

        private final PDDocument document;

        public PDFBoxDocument(InputStream pdfInputStream) {
            try {
                document = Loader.loadPDF(pdfInputStream.readAllBytes());
            } catch (IOException e) {
                throw new DocumentProcessingException(e);
            }
        }

        public PDFBoxDocument(File file) throws IOException {
            document = Loader.loadPDF(new RandomAccessReadBufferedFile(file));
        }

        @Override
        public int getNumberOfPages() {
            return document.getNumberOfPages();
        }

        @Override
        public boolean isLandscape(int pageNumber) {
            PDPage page = document.getPage(pageNumber);
            PDRectangle cropBox = page.getCropBox();
            return cropBox.getHeight() < cropBox.getWidth();
        }

        @Override
        public Pageable getPageable() {
            return new PDFPageable(document);
        }

        @Override
        public BufferedImage renderPage(int pageNumber, float scale) {
            PDFRenderer renderer = new PDFRenderer(document);
            BufferedImage bufferedImage;

            try {
                bufferedImage = renderer.renderImage(pageNumber, scale, ImageType.ARGB, RenderDestination.VIEW);
            } catch (IOException e) {
                throw new DocumentProcessingException(e);
            }

            return bufferedImage;
        }

        @Override
        public List<PDFView.SearchResult> getSearchResults(String searchText) {

            List<PDFView.SearchResult> results = new ArrayList<>();

            PDFTextStripper stripper;

            stripper = new PDFTextStripper() {

                private int pageNumber = -1;

                @Override
                protected void startPage(PDPage page) {
                    pageNumber++;
                }

                @Override
                protected void writeString(String text, List<TextPosition> textPositions) {
                    if (StringUtils.containsIgnoreCase(text, searchText)) {
                        PDFView.SearchResult
                                result = new PDFView.SearchResult(searchText, text, pageNumber, calculateMarkerPosition(searchText, text, textPositions));
                        results.add(result);
                    }
                }
            };

            Writer writer = new Writer() {

                @Override
                public void write(char[] cbuf, int off, int len) {
                    // Do nothing
                }

                @Override
                public void flush() {
                    // Do nothing
                }

                @Override
                public void close() {
                    // Do nothing
                }
            };

            try {
                stripper.writeText(document, writer);
            } catch (IOException e) {
                throw new DocumentProcessingException(e);
            }

            return results;
        }

        @Override
        public void close() {
            try {
                document.close();
            } catch (IOException e) {
                throw new DocumentProcessingException(e);
            }
        }

        private Rectangle2D calculateMarkerPosition(String searchText, String snippetText, List<TextPosition> textPositions) {
            int textPositionStartIndex = calculateTextPositionStartIndex(searchText, snippetText, textPositions);

            float x1 = Float.MAX_VALUE;
            float x2 = 0;
            float y1 = Float.MAX_VALUE;
            float y2 = 0;

            for (int textPositionIndex = textPositionStartIndex; textPositionIndex < textPositionStartIndex + searchText.length(); textPositionIndex++) {
                TextPosition position = textPositions.get(textPositionIndex);

                x1 = Math.min(x1, position.getXDirAdj());
                x2 = Math.max(x2, position.getXDirAdj() + position.getWidth());
                y1 = Math.min(y1, position.getYDirAdj() - position.getHeight());
                y2 = Math.max(y2, position.getYDirAdj());
            }

            x1 -= 2;
            x2 += 2;
            y1 -= 2;
            y2 += 2;

            return new Rectangle2D(x1, y1, x2 - x1, y2 - y1);
        }

        /**
         * Note that the number of textPositions might not be equal to the length of the snippetText.
         * So we need to account for that.
         * <p>
         * See: org.apache.pdfbox.text.PDFTextStripper.WordWithTextPositions
         */
    /*    private int calculateTextPositionStartIndex(String searchText, String snippetText, List<TextPosition> textPositions) {

            int snippetTextStartIndex = snippetText.toLowerCase().indexOf(searchText.toLowerCase());

            int startIndexDecreaseDelta = 0;

            // If any TextPosition (up to the snippetTextStartIndex) contains more then one character, we have to account for that.
            for (int i = 0; i < snippetTextStartIndex; i++) {
                int numberOfCharactersInTextPosition = textPositions.get(i).getUnicode().length();
                if (numberOfCharactersInTextPosition > 1) {
                    startIndexDecreaseDelta = startIndexDecreaseDelta + (numberOfCharactersInTextPosition - 1);
                }
            }

            return snippetTextStartIndex - startIndexDecreaseDelta;
        }
    }
}*/
