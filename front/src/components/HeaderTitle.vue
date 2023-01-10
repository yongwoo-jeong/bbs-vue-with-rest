<template>
  <h2 class="left">{{ title }}</h2>
</template>

<script>
export default {
  name: "HeaderTitle",
  props: {
    title: String,
  },
};
</script>
