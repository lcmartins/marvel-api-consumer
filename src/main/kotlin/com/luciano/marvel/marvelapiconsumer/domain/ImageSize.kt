package com.luciano.marvel.marvelapiconsumer.domain

enum class ImageSize() {
    SMALL {
        override fun portraitType(): String {
            return "portrait_small"
        }
    },
    MEDIUM {
        override fun portraitType(): String {
            return "portrait_medium"
        }
    },
    LARGE {
        override fun portraitType(): String {
            return "portrait_xlarge"
        }
    },
    FANTASTIC {
        override fun portraitType(): String {
            return "portrait_fantastic"
        }
    },
    UNCANNY {
        override fun portraitType(): String {
            return "portrait_uncanny"
        }
    },
    INCREDIBLE {
        override fun portraitType(): String {
            return "portrait_incredible"
        }
    };

    abstract fun portraitType(): String
}
